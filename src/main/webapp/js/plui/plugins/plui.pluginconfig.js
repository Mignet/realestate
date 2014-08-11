/**
 * plui.plugin.js
 * plui ²å¼þ
 */

(function($){
	
	$.pluiplugin = $.pluiplugin || {
		//server : 'http://www.szpl.gov/platform'
		server : 'http://192.168.102.20:5001/platform'
		//server : 'http://localhost:8080/webtest'
		//server : 'http://localhost:8080/PlatTutor'
	};
	
	$.pluiplugin.config = {
		//deptTreeUrl : $.pluiplugin.server + '/plui2/orgPluginDelegate/getDeptTreeJson.run',
		//deptUsersUrl : $.pluiplugin.server + '/plui2/orgPluginDelegate/getDeptUsersJson.run',
		//searchUsersUrl : $.pluiplugin.server + '/plui2/orgPluginDelegate/searchUsersJson.run',
		//getUserTreeJson : $.pluiplugin.server + '/plui2/orgPluginDelegate/getUserTreeJson.run',
		deptTreeUrl : $.pluiplugin.server + '/plui2/organization/getDeptTreeJson.go',
		deptUsersUrl : $.pluiplugin.server + '/plui2/organization/getDeptUsersJson.go',
		searchUsersUrl : $.pluiplugin.server + '/plui2/organization/searchUsersJson.go',
		getUserTreeJson : $.pluiplugin.server + '/plui2/organization/getUserTreeJson.go',
		dictUrl : $.pluiplugin.server + '/plui2/organization/getDictByCode.go'
		//dictUrl : 'http://localhost:8080/PlatTutor/plui2/dictDelegate/getDictByCode.run',
		//roleByProductUrl : 'http://localhost:8080/PlatTutor/plui2/roleDelegate/getRoleByProductId.run',
		//productTreeUrl : 'http://localhost:8080/PlatTutor/plui2/productDelegate/getProductTreeJson.run'
	};
	
})(jQuery);