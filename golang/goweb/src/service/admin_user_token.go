package service

import (
	"main.go/src/models"
	"main.go/src/utils"
)

var AdminUserTokenServ = new(AdminUserTokenService)

type AdminUserTokenService struct{}

func (a *AdminUserTokenService) ExistAdminToken(token string) (adminUserToken models.AdminUserToken, err error) {
	err = utils.GVA_DB.Where("token =?", token).First(&adminUserToken).Error
	return adminUserToken, err
}

func (a *AdminUserTokenService) DeleteAdminUserToken(token string) (err error) {
	err = utils.GVA_DB.Delete(&[]models.AdminUserToken{}, "token = ?", token).Error
	return err
}
