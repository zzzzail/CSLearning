package routers

import (
	"net/http"

	"github.com/gin-gonic/gin"
	"main.go/src/utils"
)

func InitRouter() *gin.Engine {
	router := gin.Default()
	// 配置静态文件
	router.StaticFS("/static", http.Dir(utils.GVA_CONFIG.Local.Path))
	// 加载 html 文件
	router.LoadHTMLGlob("./src/views/*")

	// 公有的路由
	publicRouterGroup := router.Group("")
	InitIndexRouter(publicRouterGroup)

	// 商城路由
	mallRouterGroup := router.Group("/mall")
	InitMallRouter(mallRouterGroup)
	// 初始化商城 API
	mallRouterApiGroup := router.Group("/mall-api-v1")
	InitMallApiRouterV1(mallRouterApiGroup)

	// 后台管理
	adminRouterGroup := router.Group("/admin")
	InitAdminRouter(adminRouterGroup)
	// 后台管理 API
	adminApiRouterGroupV1 := router.Group("/admin-api-v1")
	InitAdminApiRouterV1(adminApiRouterGroupV1)

	// todo 微信平台 API
	// todo 微信小程序 API

	return router
}
