package com.allmsi.spider.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import com.allmsi.spider.dao.GdMovieCompanyMapper;
import com.allmsi.spider.dao.GdMovieDialyMapper;
import com.allmsi.spider.dao.GdMovieHourlyMapper;
import com.allmsi.spider.dao.GdMovieHourlyRealTimeMapper;
import com.allmsi.spider.dao.GdMovieInfoMapper;
import com.allmsi.spider.dao.GdMoviePersonalMapper;
import com.allmsi.spider.dao.GdMovieTypeMapper;
import com.allmsi.spider.model.gd.GdActorsJson;
import com.allmsi.spider.model.gd.GdMovieCompany;
import com.allmsi.spider.model.gd.GdMovieDialy;
import com.allmsi.spider.model.gd.GdMovieDialyJson;
import com.allmsi.spider.model.gd.GdMovieHourly;
import com.allmsi.spider.model.gd.GdMovieHourlyJson;
import com.allmsi.spider.model.gd.GdMovieHourlyRealTime;
import com.allmsi.spider.model.gd.GdMovieInfo;
import com.allmsi.spider.model.gd.GdMovieInfoJson;
import com.allmsi.spider.model.gd.GdMoviePersonal;
import com.allmsi.spider.model.gd.GdMovieType;
import com.allmsi.spider.service.GdMovieService;
import com.allmsi.spider.util.DateUtil;
import com.allmsi.spider.util.JsonUtil;
import com.allmsi.spider.util.StrUtil;
import com.allmsi.spider.util.UUIDUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class GdMovieServiceImpl implements GdMovieService {

	private final String USER_AGENT = "Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Safari/535.19";

	private final String MOVIE_RANK_URL = "https://d.guduomedia.com/m/newRank/list/today_divide?category=NETWORK_MOVIE&orderType=asc&date=";

	private final String MOVIE_PLAY_COUNT_URL = "https://d.guduomedia.com/m/show/few_play_count/";
	private final String MOVIE_DETAIL_URL = "https://d.guduomedia.com/m/show/detail/";
	private final String MOVE_IMG_URL = "/data/images/video/";

	private boolean IS_CHANGE = false;

	private final List<String> DEFAULT_LIST = Arrays.asList("name", "rank", "showId", "platformImgs", "playCount",
			"rise", "total_play_count");

	private final String[] orderTitleArray = { "playCount", "gdi" };

	private final String[] platformIdArray = { "0", "1", "2", "3", "5" };

	private final int HOURLY_RANK_TIME = 4 * 1000;

	@Resource
	private GdMovieHourlyMapper gdMovieHourlyDao;

	@Resource
	private GdMovieDialyMapper gdMovieDialyDao;

	@Resource
	private GdMovieHourlyRealTimeMapper gdMovieHourlyRealTimeDao;

	@Resource
	private GdMovieInfoMapper gdMovieInfoDao;

	@Resource
	private GdMovieTypeMapper gdMovieTypedao;

	@Resource
	private GdMoviePersonalMapper gdMoviePersonalDao;

	@Resource
	private GdMovieCompanyMapper gdMovieCompanyDao;

	@Override
	public int getGdMovieHourlyList() {
		List<GdMovieHourly> allMovieHourlyList = new ArrayList<>();
		Set<String> showIdSet = new HashSet<>();
		Map<String, Integer> dialyPlayCountMap = getMovieDialy();
		for (int i = 0; i < orderTitleArray.length; i++) {
			for (int j = 0; j < platformIdArray.length; j++) {
				String orderTitle = orderTitleArray[i];
				String platformId = platformIdArray[j];
				List<GdMovieHourly> list = getGuduoMovieList(orderTitle, platformId);
				if (list != null && list.size() > 0) {
					for (GdMovieHourly guduoMovieHourly : list) {
						String showId = guduoMovieHourly.getShowId();
						if (!showIdSet.contains(showId)) {
							showIdSet.add(showId);
							allMovieHourlyList.add(getGdMovieHourlyPO(guduoMovieHourly, dialyPlayCountMap.get(showId)));
						}
					}
				}
				try {
					Thread.sleep(HOURLY_RANK_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		if (allMovieHourlyList != null && allMovieHourlyList.size() > 0) {
			gdMovieHourlyDao.insertGuduoMovieList(allMovieHourlyList);
			insertMovieHourlyRealTime(allMovieHourlyList);
		}
		return allMovieHourlyList.size();
	}

	private Map<String, Integer> getMovieDialy() {
		List<GdMovieDialy> movieDialyList = gdMovieDialyDao.selectMovieDialyList();
		Map<String, Integer> movieDailys = new HashMap<>();
		if (movieDialyList != null && movieDialyList.size() > 0) {
			for (GdMovieDialy gdMovieDialy : movieDialyList) {
				String showId = gdMovieDialy.getShowId();
				Integer playCountLastDate = gdMovieDialy.getPalyCountLastDate();
				movieDailys.put(showId, playCountLastDate);
			}
		}
		return movieDailys;
	}

	private GdMovieHourly getGdMovieHourlyPO(GdMovieHourly guduoMovieHourly, Integer lastDayTotalPlayCount) {
		guduoMovieHourly.setId(UUIDUtil.getUUID());
		System.out.println(guduoMovieHourly.getId());
		String date = DateUtil.stringFormat(DateUtil.dateToString(new Date()));
		guduoMovieHourly.setDate(date);

		Integer totalPlayCount = 0;
		Integer playCount = guduoMovieHourly.getPlayCount();
		String playCountShow = "";
		if (playCount != null) {
			playCount = playCount - playCount / 100;
			totalPlayCount = playCount;
			if (playCount > 9999) {
				playCountShow = playCount / 10000 + "万";
			} else {
				playCountShow = String.valueOf(playCount);
			}
		}
		guduoMovieHourly.setPlayCountShow(playCountShow);

		totalPlayCount += lastDayTotalPlayCount == null ? 0 : lastDayTotalPlayCount;
		guduoMovieHourly.setTotalPlayCount(totalPlayCount);

		Integer totalPlayCountTemp = totalPlayCount;
		if (totalPlayCountTemp != null) {
			if (lastDayTotalPlayCount != null) {
				totalPlayCountTemp = totalPlayCountTemp - totalPlayCountTemp / 100;
			}
		}
		String totalPlayCountShow = "";
		if (totalPlayCountTemp != null && totalPlayCountTemp > 9999) {
			totalPlayCountShow = String.valueOf(totalPlayCountTemp / 10000 + "万");
		} else {
			totalPlayCountShow = String.valueOf(totalPlayCountTemp);
		}
		guduoMovieHourly.setTotalPlayCountShow(totalPlayCountShow);

		String gdi = guduoMovieHourly.getGdi();
		String gdiShow = getRandom(gdi);
		guduoMovieHourly.setGdiShow(gdiShow);// gdiShow

		String hotCount = guduoMovieHourly.getHotCount();
		String hotCountShow = getRandom(hotCount);
		guduoMovieHourly.setHotCountShow(hotCountShow);// hotCountShow
		String hour = String.valueOf(DateUtil.getHour(new Date()));
		guduoMovieHourly.setHour(hour);
		return guduoMovieHourly;
	}

	private void insertMovieHourlyRealTime(List<GdMovieHourly> list) {
		if (list != null && list.size() > 0) {
			List<GdMovieHourlyRealTime> movieHourlyRealTimeList = new ArrayList<GdMovieHourlyRealTime>();
			for (GdMovieHourly guduoMovieHourly : list) {
				movieHourlyRealTimeList.add(new GdMovieHourlyRealTime(guduoMovieHourly));
			}
			if (movieHourlyRealTimeList != null && movieHourlyRealTimeList.size() > 0) {
				gdMovieHourlyRealTimeDao.updateStatus();
				gdMovieHourlyRealTimeDao.insertBatch(movieHourlyRealTimeList);
				gdMovieHourlyRealTimeDao.deleteByStatus();
			}
		}
	}

	private String getRandom(String num) {
		if (StrUtil.notEmpty(num) && StrUtil.isNumeric(num)) {

			BigDecimal numindex = new BigDecimal(num);
			BigDecimal num1 = numindex.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
			BigDecimal num2 = numindex.subtract(num1).setScale(2);
			return String.valueOf(num2);
		}
		return "";
	}

	private List<GdMovieHourly> getGuduoMovieList(String orderTitle, String platform) {
		String date = DateUtil.stringFormat(DateUtil.dateToString(new Date()));
		Long time = DateUtil.getCurrentTimeMillis();
		List<GdMovieHourly> list = new ArrayList<>();
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(
				MOVIE_RANK_URL + date + "&platformId=" + platform + "&orderTitle=" + orderTitle + "&t=" + time);
		httpGet.setHeader("user-agent", USER_AGENT);
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entity = response.getEntity();// 调用getEntity()方法获取到一个HttpEntity实例
			if (entity != null) {
				String json = null;
				try {
					json = EntityUtils.toString(entity, "UTF-8");
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
				JsonArray jsonArray = jsonObject.getAsJsonArray("data");
				for (JsonElement e : jsonArray) {
					isChange(e);
					GdMovieHourlyJson movieJson = new Gson().fromJson(e, GdMovieHourlyJson.class);
					list.add(new GdMovieHourly(movieJson));
				}
				System.out.println(" platform:  " + platform + "按orderTitle排序" + orderTitle + "影片数：" + list.size());
			}
		}
		return list;
	}

	private void isChange(JsonElement e) {
		if (!IS_CHANGE) {
			List<String> list = JsonUtil.getAllKeys(e.toString());
			for (String string : list) {
				if (!DEFAULT_LIST.contains(string)) {
					IS_CHANGE = true;
					break;
				}
			}
		}
	}

	@Override
	public int getMovieDialyPlayCountList() {
		List<GdMovieDialy> gdMovieDialyList = getMovieDialyPlayCount();
		int count = 0;
		if (gdMovieDialyList != null && gdMovieDialyList.size() > 0) {
			count = gdMovieDialyDao.insetBatch(gdMovieDialyList);
		}
		return count;

	}

	private List<GdMovieDialy> getMovieDialyPlayCount() {
		List<String> showIdList = selectMovieHourlyList();
		List<GdMovieDialy> movieDialyList = new ArrayList<>();
		int total = 0;
		if (showIdList != null && showIdList.size() > 0) {
			for (String showId : showIdList) {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(MOVIE_PLAY_COUNT_URL + showId);
				httpGet.setHeader("user-agent", USER_AGENT);
				CloseableHttpResponse response = null;
				try {
					response = httpclient.execute(httpGet);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String json = null;
						try {
							json = EntityUtils.toString(entity, "UTF-8");
						} catch (ParseException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Gson gson = new Gson();
						GdMovieDialyJson movieDialyJson = gson.fromJson(json, GdMovieDialyJson.class);
						if (movieDialyJson != null) {
							GdMovieDialy movieDialy = new GdMovieDialy(movieDialyJson, showId);
							movieDialyList.add(movieDialy);
						}
					}
					total++;
					if (total % 10 == 0) {
						System.out.println("total" + total);
						try {
							Thread.sleep(HOURLY_RANK_TIME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return movieDialyList;
	}

	private List<String> selectMovieHourlyList() {
		List<GdMovieHourly> gdMovieHourlylist = gdMovieHourlyDao.selectMovieHourlyList();
		List<String> showIdList = new ArrayList<>();
		if (gdMovieHourlylist != null && gdMovieHourlylist.size() > 0) {
			for (GdMovieHourly gdMovieHourly : gdMovieHourlylist) {
				String showId = gdMovieHourly.getShowId();
				showIdList.add(showId);
			}
		}
		return showIdList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getGdMovieInfoList() {
		Map<String, Object> allMovieInfoMap = getGdMovieInfo();
		List<GdMovieInfo> gdMovieInfoList = (List<GdMovieInfo>) allMovieInfoMap.get("gdMovieInfoList");
		List<GdMovieType> gdMovieTypeList = (List<GdMovieType>) allMovieInfoMap.get("gdMovieTypeList");
		List<GdMoviePersonal> gdMoviePersonList = (List<GdMoviePersonal>) allMovieInfoMap.get("gdMoviePersonList");
		List<GdMovieCompany> gdMovieCompanyList = (List<GdMovieCompany>) allMovieInfoMap.get("gdMovieCompanyList");
		int count = 0;
		if (gdMovieTypeList != null && gdMovieTypeList.size() > 0) {
			count = gdMovieTypedao.insertBatch(gdMovieTypeList);
		}
		if (gdMoviePersonList != null && gdMoviePersonList.size() > 0) {
			count = gdMoviePersonalDao.insertBatch(gdMoviePersonList);
		}
		if (gdMovieCompanyList != null && gdMovieCompanyList.size() > 0) {
			count = gdMovieCompanyDao.insertBatch(gdMovieCompanyList);
		}
		if (gdMovieInfoList != null && gdMovieInfoList.size() > 0) {
			count = gdMovieInfoDao.insertBatch(gdMovieInfoList);
		}
		return count;
	}

	private Map<String, Object> getGdMovieInfo() {
		List<String> showIdList = getshowIdList();
		List<GdMovieInfo> gdMovieInfoList = new ArrayList<>();
		List<GdMovieType> gdMovieTypeList = new ArrayList<>();
		List<GdMoviePersonal> gdMoviePersonList = new ArrayList<>();
		List<GdMovieCompany> gdMovieCompanyList = new ArrayList<>();
		Map<String, Object> allMovieInfoMap = new HashMap<>();
		Long time = DateUtil.getCurrentTimeMillis();
		int total = 0;
		if (showIdList != null && showIdList.size() > 0) {
			for (String showId : showIdList) {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet httpGet = new HttpGet(MOVIE_DETAIL_URL + showId + "?t=" + time);
				httpGet.setHeader("user-agent", USER_AGENT);
				CloseableHttpResponse response = null;
				try {
					response = httpclient.execute(httpGet);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (response.getStatusLine().getStatusCode() == 200) {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						String json = null;
						try {
							json = EntityUtils.toString(entity, "UTF-8");
						} catch (ParseException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						Gson gson = new Gson();
						GdMovieInfoJson movieInfoJson = gson.fromJson(json, GdMovieInfoJson.class);
						String id = UUIDUtil.getUUID();
						if (movieInfoJson != null) {
							// 影片基本信息
							String coverUrl = movieInfoJson.getCover();
							String coverImage = "";
							if (StrUtil.notEmpty(coverUrl)) {
								URL url = null;
								InputStream inputStream = null;
								OutputStream outputStream = null;
								HttpURLConnection con = null;
								try {
									url = new URL(coverUrl);
									try {
										con = (HttpURLConnection) url.openConnection();
										inputStream = con.getInputStream();
										File file = new File(MOVE_IMG_URL + id + ".jpg");
										outputStream = new FileOutputStream(file);
										int n = -1;
										byte b[] = new byte[1024];
										while ((n = inputStream.read(b)) != -1) {
											outputStream.write(b, 0, n);
										}
										outputStream.flush();
									} catch (IOException e) {
										e.printStackTrace();
									}
								} catch (MalformedURLException e1) {
									e1.printStackTrace();
								}
								coverImage = "video/" + id + ".jpg";
							}
							GdMovieInfo movieInfo = new GdMovieInfo(movieInfoJson, id, coverImage);
							gdMovieInfoList.add(movieInfo);
							// 影片类型
							String typeName = "";
							int typeOrder = 0;
							String type = movieInfoJson.getType();
							if (StrUtil.notEmpty(type)) {
								String[] typeArray = type.split("/");
								for (int i = 0; i < typeArray.length; i++) {
									typeName = typeArray[i];
									typeOrder = i + 1;
									gdMovieTypeList.add(new GdMovieType(showId, typeName.trim(), typeOrder));
								}
							}
							// 人员信息
							String personName = "";
							String personType = "";
							int sequence = 0;
							if (StrUtil.notEmpty(movieInfoJson.getDirector())) {
								String[] directorArray = movieInfoJson.getDirector().split("/");
								for (int i = 0; i < directorArray.length; i++) {
									personName = directorArray[i];
									sequence = i + 1;
									personType = "director";
									gdMoviePersonList
											.add(new GdMoviePersonal(showId, personName, personType, sequence));
								}
							}
							if (StrUtil.notEmpty(movieInfoJson.getScript_writer())) {
								String[] ScriptWriterArray = movieInfoJson.getScript_writer().split("/");
								for (int i = 0; i < ScriptWriterArray.length; i++) {
									personName = ScriptWriterArray[i];
									sequence = i + 1;
									personType = "script_writer";
									gdMoviePersonList
											.add(new GdMoviePersonal(showId, personName, personType, sequence));
								}
							}
							if (StrUtil.notEmpty(movieInfoJson.getExecutive_producer())) {
								String[] exeProduceArray = movieInfoJson.getExecutive_producer().split("/");
								for (int i = 0; i < exeProduceArray.length; i++) {
									personName = exeProduceArray[i];
									sequence = i + 1;
									personType = "executive_producer";
									gdMoviePersonList
											.add(new GdMoviePersonal(showId, personName, personType, sequence));
								}
							}
							if (StrUtil.notEmpty(movieInfoJson.getProducer())) {
								String[] produceArray = movieInfoJson.getProducer().split("/");
								for (int i = 0; i < produceArray.length; i++) {
									personName = produceArray[i];
									sequence = i + 1;
									personType = "producer";
									gdMoviePersonList
											.add(new GdMoviePersonal(showId, personName, personType, sequence));
								}
							}
							if (StrUtil.notEmpty(movieInfoJson.getPublisher())) {
								String[] publishArray = movieInfoJson.getPublisher().split("/");
								for (int i = 0; i < publishArray.length; i++) {
									personName = publishArray[i];
									sequence = i + 1;
									personType = "publisher";
									gdMoviePersonList
											.add(new GdMoviePersonal(showId, personName, personType, sequence));
								}
							}

							JsonParser jsonParser = new JsonParser();
							if (movieInfoJson.getActor_info() != null) {
								String jsonString = new Gson().toJson(movieInfoJson.getActor_info());
								JsonArray jsonArray = jsonParser.parse(jsonString).getAsJsonArray();
								for (JsonElement e : jsonArray) {
									GdActorsJson actorsJson = new Gson().fromJson(e, GdActorsJson.class);
									personType = "actor";
									gdMoviePersonList.add(new GdMoviePersonal(actorsJson, showId, personType));
								}
							}

							// 公司信息
							String companyName = "";
							String companyType = "";
							if (movieInfoJson.getProducers() != null && movieInfoJson.getProducers().length > 0) {
								String[] producersArray = movieInfoJson.getProducers();
								for (int i = 0; i < producersArray.length; i++) {
									companyName = producersArray[i];
									companyType = "producers";
									sequence = i + 1;
									gdMovieCompanyList
											.add(new GdMovieCompany(showId, companyName, companyType, sequence));
								}
							}
							if (movieInfoJson.getPublishers() != null && movieInfoJson.getPublishers().length > 0) {
								String[] publishersArray = movieInfoJson.getPublishers();
								for (int i = 0; i < publishersArray.length; i++) {
									companyName = publishersArray[i];
									companyType = "publishers";
									sequence = i + 1;
									gdMovieCompanyList
											.add(new GdMovieCompany(showId, companyName, companyType, sequence));
								}
							}
						}
					}
					total++;
					if (total % 10 == 0) {
						try {
							Thread.sleep(HOURLY_RANK_TIME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		allMovieInfoMap.put("gdMovieInfoList", gdMovieInfoList);
		allMovieInfoMap.put("gdMovieTypeList", gdMovieTypeList);
		allMovieInfoMap.put("gdMoviePersonList", gdMoviePersonList);
		allMovieInfoMap.put("gdMovieCompanyList", gdMovieCompanyList);
		return allMovieInfoMap;
	}

	private List<String> getshowIdList() {
		List<String> showIdList = new ArrayList<>();
		Set<String> movieInfoSet = selectMovieInfoShowIdList();
		List<String> hourlyRealTimeShowIdList = gdMovieHourlyRealTimeDao.selectMovieHourlyRealTimeList();
		if (hourlyRealTimeShowIdList != null && hourlyRealTimeShowIdList.size() > 0) {
			for (String showId : hourlyRealTimeShowIdList) {
				if (!movieInfoSet.contains(showId)) {
					showIdList.add(showId);
				}
			}
		}
		System.out.println("需要根据showId抓取的详情有" + showIdList.size() + "条");
		return showIdList;
	}

	private Set<String> selectMovieInfoShowIdList() {
		List<String> gdMovieInfolist = gdMovieInfoDao.selectShowIdList();
		Set<String> showIdList = new HashSet<>();
		if (gdMovieInfolist != null && gdMovieInfolist.size() > 0) {
			for (String showId : gdMovieInfolist) {
				showIdList.add(showId);
			}
		}
		return showIdList;
	}
}
