package service

import (
	"main.go/src/models"
	"main.go/src/utils"
)

var MallUserServ = new(MallUserService)

type MallUserService struct{}

func (m *MallUserService) GetMallUserByUserId(userId int) (mallUser models.MallUser, err error) {
	err = utils.GVA_DB.Where("user_id = ?", userId).First(&mallUser).Error
	return
}
