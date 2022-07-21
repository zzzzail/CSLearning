package service

import (
	"errors"
	"strconv"
	"strings"
	"time"

	"gorm.io/gorm"
	"main.go/src/models"
	"main.go/src/utils"
)

var AdminUserServ = new(AdminUserService)

type AdminUserService struct{}

func (a *AdminUserService) CreateAdminUser(adminUser models.AdminUser) (err error) {
	if !errors.Is(
		utils.GVA_DB.Where(
			"login_user_name = ?", adminUser.LoginUserName).First(
			&models.AdminUser{}).Error, gorm.ErrRecordNotFound) {
		return errors.New("存在相同用户名")
	}
	err = utils.GVA_DB.Create(&adminUser).Error
	return err
}

func (a *AdminUserService) ExistAdminToken() {

}

type AdminUserLoginParam struct {
	UserName    string
	PasswordMd5 string
}

// AdminLogin 管理员登陆
func (m *AdminUserService) AdminLogin(params AdminUserLoginParam) (adminUser models.AdminUser, adminUserToken models.AdminUserToken, err error) {
	err = utils.GVA_DB.Where("login_user_name = ? AND login_password = ?", params.UserName, params.PasswordMd5).First(&adminUser).Error
	if adminUser != (models.AdminUser{}) {
		token := getNewToken(time.Now().UnixNano()/1e6, int(adminUser.Id))
		utils.GVA_DB.Where("id", adminUser.Id).First(&adminUserToken)
		nowDate := time.Now()
		// 48 小时过期
		expireTime, _ := time.ParseDuration("48h")
		expireDate := nowDate.Add(expireTime)
		// 没有 token 新增，有 token 则更新
		if adminUserToken == (models.AdminUserToken{}) {
			adminUserToken.AdminUserId = adminUser.Id
			adminUserToken.Token = token
			adminUserToken.UpdateTime = nowDate
			adminUserToken.ExpireTime = expireDate
			if err = utils.GVA_DB.Create(&adminUserToken).Error; err != nil {
				return
			}
		} else {
			adminUserToken.Token = token
			adminUserToken.UpdateTime = nowDate
			adminUserToken.ExpireTime = expireDate
			if err = utils.GVA_DB.Save(&adminUserToken).Error; err != nil {
				return
			}
		}
	}
	return adminUser, adminUserToken, err

}

func getNewToken(timeInt int64, userId int) (token string) {
	var build strings.Builder
	build.WriteString(strconv.FormatInt(timeInt, 10))
	build.WriteString(strconv.Itoa(userId))
	build.WriteString(utils.GenValidateCode(6))
	return utils.MD5V([]byte(build.String()))
}
