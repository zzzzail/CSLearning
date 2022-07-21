package controllers

import (
	"fmt"

	"github.com/gin-gonic/gin"
	"main.go/src/service"
	"main.go/src/utils"
)

var IndexCtl = new(IndexController)

type IndexController struct{}

func (i *IndexController) Index(c *gin.Context) {

	mallUser, _ := service.MallUserServ.GetMallUserByUserId(1)
	fmt.Print(mallUser)

	// response.Ok(c)
	utils.RenderHtml(c, "index", &gin.H{
		"title": "heihei" + mallUser.LoginName,
	})
}
