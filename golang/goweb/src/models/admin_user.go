package models

// AdminUser 结构体
type AdminUser struct {
	Id            int    `json:"id" form:"id" gorm:"primarykey;AUTO_INCREMENT"`
	LoginUserName string `json:"loginUserName" form:"loginUserName" gorm:"column:login_user_name;comment:管理员登陆名称;type:varchar(50);"`
	LoginPassword string `json:"loginPassword" form:"loginPassword" gorm:"column:login_password;comment:管理员登陆密码;type:varchar(50);"`
	NickName      string `json:"nickName" form:"nickName" gorm:"column:nick_name;comment:管理员显示昵称;type:varchar(50);"`
	Locked        int    `json:"locked" form:"locked" gorm:"column:locked;comment:是否锁定 0未锁定 1已锁定无法登陆;type:tinyint"`
}

func (AdminUser) TableName() string {
	return "t_admin_user"
}
