package service

import (
	"main.go/src/models"
	"main.go/src/utils"
)

var MallUserTokenServ = new(MallUserTokenService)

type MallUserTokenService struct{}

func (m *MallUserTokenService) ExistUserToken(token string) (mallUserToken models.MallUserToken, err error) {
	err = utils.GVA_DB.Where("token = ?", token).First(&mallUserToken).Error
	return mallUserToken, err
}

func (m *MallUserTokenService) DeleteMallUserToken(token string) (err error) {
	err = utils.GVA_DB.Delete(&[]models.MallUserToken{}, "token =?", token).Error
	return err
}
