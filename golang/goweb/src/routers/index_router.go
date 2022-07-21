package routers

import (
	"github.com/gin-gonic/gin"
	"main.go/src/controllers"
)

func InitIndexRouter(Router *gin.RouterGroup) {
	Router.GET("/index", controllers.IndexCtl.Index)
}
