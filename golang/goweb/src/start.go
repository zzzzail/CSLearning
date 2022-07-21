package src

import (
	"fmt"
	"time"

	"github.com/fvbock/endless"
	"github.com/gin-gonic/gin"
	"go.uber.org/zap"
	"main.go/src/routers"
	"main.go/src/utils"
)

type server interface {
	ListenAndServe() error
}

func Start() {
	utils.GVA_VP = utils.InitViper() // 初始化Viper
	utils.GVA_LOG = utils.InitZap()  // 初始化zap日志库
	utils.GVA_DB = utils.InitGorm()  // gorm 连接数据库

	Router := routers.InitRouter()
	address := fmt.Sprintf(":%d", utils.GVA_CONFIG.System.Addr)
	server := initServer(address, Router)
	utils.GVA_LOG.Info("server run success on ", zap.String("address", address))
	utils.GVA_LOG.Error(server.ListenAndServe().Error())
}

func initServer(address string, router *gin.Engine) server {
	s := endless.NewServer(address, router)
	s.ReadHeaderTimeout = 20 * time.Second
	s.WriteTimeout = 20 * time.Second
	s.MaxHeaderBytes = 1 << 20
	return s
}
