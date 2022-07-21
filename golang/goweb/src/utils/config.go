package utils

import (
	"github.com/spf13/viper"
	"go.uber.org/zap"
	"gorm.io/gorm"
)

var (
	GVA_VP     *viper.Viper
	GVA_LOG    *zap.Logger
	GVA_DB     *gorm.DB
	GVA_CONFIG Config
)

type Config struct {
	// 日志配置
	Zap Zap `mapstructure:"zap" json:"zap" yaml:"zap"`
	// 系统配置
	System System `mapstructure:"system" json:"system" yaml:"system"`
	// gorm
	Mysql Mysql `mapstructure:"mysql" json:"mysql" yaml:"mysql"`
	// oss
	Local Local `mapstructure:"local" json:"local" yaml:"local"`
}

type Mysql struct {
	Path         string `mapstructure:"path" json:"path" yaml:"path"`                             // 服务器地址
	Port         string `mapstructure:"port" json:"port" yaml:"port"`                             // 端口
	Config       string `mapstructure:"config" json:"config" yaml:"config"`                       // 高级配置
	Dbname       string `mapstructure:"db-name" json:"dbname" yaml:"db-name"`                     // 数据库名
	Username     string `mapstructure:"username" json:"username" yaml:"username"`                 // 数据库用户名
	Password     string `mapstructure:"password" json:"password" yaml:"password"`                 // 数据库密码
	MaxIdleConns int    `mapstructure:"max-idle-conns" json:"maxIdleConns" yaml:"max-idle-conns"` // 空闲中的最大连接数
	MaxOpenConns int    `mapstructure:"max-open-conns" json:"maxOpenConns" yaml:"max-open-conns"` // 打开到数据库的最大连接数
	LogMode      string `mapstructure:"log-mode" json:"logMode" yaml:"log-mode"`                  // 是否开启Gorm全局日志
	LogZap       bool   `mapstructure:"log-zap" json:"logZap" yaml:"log-zap"`                     // 是否通过zap写入日志文件
}

func (m *Mysql) Dsn() string {
	return m.Username + ":" + m.Password + "@tcp(" + m.Path + ":" + m.Port + ")/" + m.Dbname + "?" + m.Config
}

type Local struct {
	Path string `mapstructure:"path" json:"path" yaml:"path"` // 本地文件路径
}
type System struct {
	// 环境值
	Env string `mapstructure:"env" json:"env" yaml:"env"`
	// 端口值
	Addr int `mapstructure:"addr" json:"addr" yaml:"addr"`
	// 数据库类型:mysql(默认)|sqlite|sqlserver|postgresql
	DbType string `mapstructure:"db-type" json:"dbType" yaml:"db-type"`
	// Oss类型
	OssType string `mapstructure:"oss-type" json:"ossType" yaml:"oss-type"`
	// 多点登录拦截
	UseMultipoint bool `mapstructure:"use-multipoint" json:"useMultipoint" yaml:"use-multipoint"`
	LimitCountIP  int  `mapstructure:"iplimit-count" json:"iplimitCount" yaml:"iplimit-count"`
	LimitTimeIP   int  `mapstructure:"iplimit-time" json:"iplimitTime" yaml:"iplimit-time"`
}

type Zap struct {
	Level         string `mapstructure:"level" json:"level" yaml:"level"`                           // 级别
	Format        string `mapstructure:"format" json:"format" yaml:"format"`                        // 输出
	Prefix        string `mapstructure:"prefix" json:"prefix" yaml:"prefix"`                        // 日志前缀
	Director      string `mapstructure:"director" json:"director"  yaml:"director"`                 // 日志文件夹
	ShowLine      bool   `mapstructure:"show-line" json:"showLine" yaml:"showLine"`                 // 显示行
	EncodeLevel   string `mapstructure:"encode-level" json:"encodeLevel" yaml:"encode-level"`       // 编码级
	StacktraceKey string `mapstructure:"stacktrace-key" json:"stacktraceKey" yaml:"stacktrace-key"` // 栈名
	LogInConsole  bool   `mapstructure:"log-in-console" json:"logInConsole" yaml:"log-in-console"`  // 输出控制台
}
