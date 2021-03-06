/*
 *解析页面统计中URL对应的功能 
 */
var _ArrayCount=0;
var _urlArray = new Array();
var _urlNameArray = new Array();

function decodeUrlPageFunc(sysid,url)
{
	if(!sysid||!url)
	{
		return url|"未知";
	}
	var key = sysid.trim()+"_"+url.trim();
	for(var i=0;i<_urlArray.length;i++)
	{
		if(key==_urlArray[i])
		{
			return _urlNameArray[i];
		}
	}
}
function decodeUrlPageFunc2(sysUrl)
{
	for(var i=0;i<_urlArray.length;i++)
	{
		if(sysUrl==_urlArray[i])
		{
			return _urlNameArray[i];
		}
	}
}
/*--------------商店-----------------*/
_urlArray[_ArrayCount]="2_/MobileAPI/GetRecommendProducts.ashx";
_urlNameArray[_ArrayCount++]="首页推荐";
_urlArray[_ArrayCount]="2_/MobileAPI/GetProductByExtendType.ashx";
_urlNameArray[_ArrayCount++]="OPPO专区列表";
_urlArray[_ArrayCount]="2_/MobileAPI/GetCategory.ashx";
_urlNameArray[_ArrayCount++]="应用游戏铃声壁纸分类";
_urlArray[_ArrayCount]="2_/MobileAPI/Search.ashx";
_urlNameArray[_ArrayCount++]="搜索资源";
_urlArray[_ArrayCount]="2_/MobileAPI/SearchBestKeyWord.ashx";
_urlNameArray[_ArrayCount++]="获取热门搜索词";
_urlArray[_ArrayCount]="2_/MobileAPI/GetProducts.ashx";
_urlNameArray[_ArrayCount++]="获取产品列表";
_urlArray[_ArrayCount]="2_/MobileAPI/GetProductDetail.ashx";
_urlNameArray[_ArrayCount++]="获取产品详细信息";
_urlArray[_ArrayCount]="2_/MobileAPI/AddCommentAndRating.ashx";
_urlNameArray[_ArrayCount++]="用户评分评论";
_urlArray[_ArrayCount]="2_/MobileAPI/GetComments.ashx";
_urlNameArray[_ArrayCount++]="获取用户评论";
_urlArray[_ArrayCount]="2_/MobileAPI/SendAdvice.ashx";
_urlNameArray[_ArrayCount++]="投诉与建议";
_urlArray[_ArrayCount]="2_/MobileAPI/GetProductsByUserID.ashx";
_urlNameArray[_ArrayCount++]="获取开发者的所有应用";
_urlArray[_ArrayCount]="2_/MobileAPI/CheckUpgrade.ashx";
_urlNameArray[_ArrayCount++]="获取应用更新信息";
_urlArray[_ArrayCount]="2_/MobileAPI/CheckInnerUpgrade.ashx";
_urlNameArray[_ArrayCount++]="自动升级接口";
_urlArray[_ArrayCount]="2_/MobileAPI/GetPurchaseStatus.ashx";
_urlNameArray[_ArrayCount++]="获取产品购买状态";
_urlArray[_ArrayCount]="2_/MobileAPI/PurchaseProduct.ashx";
_urlNameArray[_ArrayCount++]="购买产品";
_urlArray[_ArrayCount]="2_/MobileAPI/ListTheme.ashx";
_urlNameArray[_ArrayCount++]="获取主题列表";
_urlArray[_ArrayCount]="2_/MobileAPI/GetThemeDetail.ashx";
_urlNameArray[_ArrayCount++]="获取主题详情";
_urlArray[_ArrayCount]="2_/MobileAPI/GetDownloadStatus.ashx";
_urlNameArray[_ArrayCount++]="获取产品下载状态";
_urlArray[_ArrayCount]="2_/MobileAPI/GetTopicCategory.ashx";
_urlNameArray[_ArrayCount++]="获取专题列表";
_urlArray[_ArrayCount]="2_/MobileAPI/GetTopicProducts.ashx";
_urlNameArray[_ArrayCount++]="获取专题产品列表";
_urlArray[_ArrayCount]="2_/MobileAPI/ListRingtone.ashx";
_urlNameArray[_ArrayCount++]="获取铃声列表";
_urlArray[_ArrayCount]="2_/MobileAPI/GetRingToneDetail.ashx";
_urlNameArray[_ArrayCount++]="获取铃声详情";
_urlArray[_ArrayCount]="2_/MobileAPI/ListPicture.ashx";
_urlNameArray[_ArrayCount++]="获取壁纸列表";
_urlArray[_ArrayCount]="2_/MobileAPI/GetPictureDetail.ashx";
_urlNameArray[_ArrayCount++]="获取壁纸详情";
_urlArray[_ArrayCount]="2_/MobileAPI/ValidateResource.ashx";
_urlNameArray[_ArrayCount++]="验证资源支持程度";
_urlArray[_ArrayCount]="2_/MobileAPI/AddPointAction.ashx";
_urlNameArray[_ArrayCount++]="调用积分奖励接口";
_urlArray[_ArrayCount]="2_/MobileAPI/ListDownloadStatus.ashx";
_urlNameArray[_ArrayCount++]="批量获取下载状态";
_urlArray[_ArrayCount]="2_/MobileAPI/GetFestivalImage.ashx";
_urlNameArray[_ArrayCount++]="获取节日开机图片";
_urlArray[_ArrayCount]="2_/MobileAPI/ListDailyRecommendProducts.ashx";
_urlNameArray[_ArrayCount++]="获取每日推荐列表";
_urlArray[_ArrayCount]="2_/MobileAPI/ListRelativeProducts.ashx";
_urlNameArray[_ArrayCount++]="获取资源相关推荐列表";
_urlArray[_ArrayCount]="2_/MobileAPI/ListLatelyPublishProducts.ashx";
_urlNameArray[_ArrayCount++]="获取最新上架资源列表";
_urlArray[_ArrayCount]="2_/MobileAPI/ListRankingProducts.ashx";
_urlNameArray[_ArrayCount++]="获取排行榜资源列表";
_urlArray[_ArrayCount]="2_/MobileAPI/ListCollectionProducts.ashx";
_urlNameArray[_ArrayCount++]="获取收藏列表";
_urlArray[_ArrayCount]="2_/MobileAPI/ChangeCollectionStatus.ashx";
_urlNameArray[_ArrayCount++]="收藏/取消收藏资源";
_urlArray[_ArrayCount]="2_/MobileAPI/ListBaiduProducts.ashx";
_urlNameArray[_ArrayCount++]="获取百度列表";
_urlArray[_ArrayCount]="2_/MobileAPI/DownloadBaiduProduct.ashx";
_urlNameArray[_ArrayCount++]="下载百度资源";
_urlArray[_ArrayCount]="2_/MobileAPI/GetProductIdByPackageName.ashx";
_urlNameArray[_ArrayCount++]="根据包名获取PID";
_urlArray[_ArrayCount]="2_/MobileAPI/GetProductDetailByPackageName.ashx";
_urlNameArray[_ArrayCount++]="根据包名获取软件详情";
_urlArray[_ArrayCount]="2_/MobileAPI/GetProductUpgradeInfo.ashx";
_urlNameArray[_ArrayCount++]="获取其他平台更新信息";


/*--------------书城-----------------*/
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/UploadOperator";
_urlNameArray[_ArrayCount++]="用户行为数据上传";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/SendActivateEmail";
_urlNameArray[_ArrayCount++]="发送激活邮件";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/SyncBookList";
_urlNameArray[_ArrayCount++]="同步书籍状态";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetRecommendBookList";
_urlNameArray[_ArrayCount++]="获取推荐图书列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetBroadcastList";
_urlNameArray[_ArrayCount++]="获取所有公告列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/FetchTopicList";
_urlNameArray[_ArrayCount++]="获取所有专题列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetSpecifiedTopic";
_urlNameArray[_ArrayCount++]="获取书单类型的专题信息";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetBookRankList";
_urlNameArray[_ArrayCount++]="获取书籍排行榜列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetSpecifiedBookRankList";
_urlNameArray[_ArrayCount++]="获取具体类型排行榜列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetBookCategory";
_urlNameArray[_ArrayCount++]="获取所有数据分类列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetSpecifiedBookList";
_urlNameArray[_ArrayCount++]="获取具体类别书籍列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetSpecifiedBook";
_urlNameArray[_ArrayCount++]="获取具体书籍详细信息";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/AddComment";
_urlNameArray[_ArrayCount++]="用户撰写书评";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetCommentList";
_urlNameArray[_ArrayCount++]="获取所有书评";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/FetchBookListOfAuthor";
_urlNameArray[_ArrayCount++]="获取作者的所有书籍列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetPushedBookList";
_urlNameArray[_ArrayCount++]="获取推送书籍列表";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetSpecifiedBookCatalog";
_urlNameArray[_ArrayCount++]="获取具体书籍现有目录";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetPopularKeywords";
_urlNameArray[_ArrayCount++]="获取热门搜索的7个词汇";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/QuerySpecifiedKeyword";
_urlNameArray[_ArrayCount++]="书籍搜索";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetDownloadURL";
_urlNameArray[_ArrayCount++]="获取资源下载地址";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/VerifyResource";
_urlNameArray[_ArrayCount++]="资源激活验证";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/Checkout";
_urlNameArray[_ArrayCount++]="请求支付";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/CheckoutConfirm";
_urlNameArray[_ArrayCount++]="支付确认";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/Achievement";
_urlNameArray[_ArrayCount++]="用户成就数据上传";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/FetchAchievement";
_urlNameArray[_ArrayCount++]="获取用户成就";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/FeedBack";
_urlNameArray[_ArrayCount++]="用户意见反馈";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/Upgrade";
_urlNameArray[_ArrayCount++]="软件升级";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/CustomSign";
_urlNameArray[_ArrayCount++]="提交用户个性签名";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetCustomSign";
_urlNameArray[_ArrayCount++]="获取用户个性签名";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/GetAchieveRankList";
_urlNameArray[_ArrayCount++]="获取用户成就排名";
_urlArray[_ArrayCount]="3_/ebook_mobile/getapi/CheckInnerUpgrade";
_urlNameArray[_ArrayCount++]="检查升级";

/*--------------音乐-----------------*/
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_first_magazine_list";
_urlNameArray[_ArrayCount++]="音乐杂志首页推荐";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_magazine_category_list";
_urlNameArray[_ArrayCount++]="杂志分类";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_magazine_list";
_urlNameArray[_ArrayCount++]="杂志列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_magazine_Content";
_urlNameArray[_ArrayCount++]="杂志详情";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/do_comment";
_urlNameArray[_ArrayCount++]="评论杂志";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_comment_list";
_urlNameArray[_ArrayCount++]="评论列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_topic_list";
_urlNameArray[_ArrayCount++]="专题列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_topic";
_urlNameArray[_ArrayCount++]="专题详情";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_comment_rating";
_urlNameArray[_ArrayCount++]="评分查询";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_top_list";
_urlNameArray[_ArrayCount++]="音乐库排行榜列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_one_top";
_urlNameArray[_ArrayCount++]="排行榜下歌曲列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_latest_albums";
_urlNameArray[_ArrayCount++]="专辑列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_album";
_urlNameArray[_ArrayCount++]="专辑详情";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_singer_category";
_urlNameArray[_ArrayCount++]="歌手一级分类列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_category_singers";
_urlNameArray[_ArrayCount++]="歌手二级分类列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_singer";
_urlNameArray[_ArrayCount++]="歌手详情";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_song";
_urlNameArray[_ArrayCount++]="歌曲详情";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_singer_songs";
_urlNameArray[_ArrayCount++]="歌手的所有歌曲列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/search";
_urlNameArray[_ArrayCount++]="搜索";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/get_hot_keys";
_urlNameArray[_ArrayCount++]="获取热门搜索标签";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/fav_action";
_urlNameArray[_ArrayCount++]="收藏/取消收藏";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/sync_favs";
_urlNameArray[_ArrayCount++]="同步收藏列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/upload_fav_group";
_urlNameArray[_ArrayCount++]="新建/编辑子收藏夹";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/del_fav_group";
_urlNameArray[_ArrayCount++]="删除子收藏夹";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/down_song";
_urlNameArray[_ArrayCount++]="下载歌曲";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/pay_combo";
_urlNameArray[_ArrayCount++]="选择支付套餐";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/query_combo";
_urlNameArray[_ArrayCount++]="查询套餐剩余";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/query_free_down";
_urlNameArray[_ArrayCount++]="查询可以免费再次下载的歌曲列表";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/feedback";
_urlNameArray[_ArrayCount++]="反馈";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/query_activate";
_urlNameArray[_ArrayCount++]="查询支付状态";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/check_can_download";
_urlNameArray[_ArrayCount++]="查询歌曲能否下载";
_urlArray[_ArrayCount]="4_/MobileAPI/CheckInnerUpgrade.ashx";
_urlNameArray[_ArrayCount++]="检查升级";
_urlArray[_ArrayCount]="4_/music_mobile/getapi/send_activate_email";
_urlNameArray[_ArrayCount++]="发送激活邮件";