package middleware

import (
	"time"

	"github.com/gin-gonic/gin"
	adminService "main.go/src/service/admin"
	mallService "main.go/src/service/mall"
	"main.go/src/utils"
)

var manageAdminUserTokenService = adminService.AdminUserTokenServ
var mallUserTokenService = mallService.MallUserTokenServ

func AdminJWTAuth() gin.HandlerFunc {
	return func(c *gin.Context) {
		token := c.Request.Header.Get("token")
		if token == "" {
			utils.FailWithDetailed(nil, "未登录或非法访问", c)
			c.Abort()
			return
		}
		mallAdminUserToken, err := manageAdminUserTokenService.ExistAdminToken(token)
		if err != nil {
			utils.FailWithDetailed(nil, "未登录或非法访问", c)
			c.Abort()
			return
		}
		if time.Now().After(mallAdminUserToken.ExpireTime) {
			utils.FailWithDetailed(nil, "授权已过期", c)
			err = manageAdminUserTokenService.DeleteAdminUserToken(token)
			if err != nil {
				return
			}
			c.Abort()
			return
		}
		c.Next()
	}

}

func UserJWTAuth() gin.HandlerFunc {
	return func(c *gin.Context) {
		token := c.Request.Header.Get("token")
		if token == "" {
			utils.UnLogin(nil, c)
			c.Abort()
			return
		}
		mallUserToken, err := mallUserTokenService.ExistUserToken(token)
		if err != nil {
			utils.UnLogin(nil, c)
			c.Abort()
			return
		}
		if time.Now().After(mallUserToken.ExpireTime) {
			utils.FailWithDetailed(nil, "授权已过期", c)
			err = mallUserTokenService.DeleteMallUserToken(token)
			if err != nil {
				return
			}
			c.Abort()
			return
		}
		c.Next()
	}
}
