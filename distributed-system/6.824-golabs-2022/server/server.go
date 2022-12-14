package server

import (
	"fmt"
	"log"
	"net/http"
	"strconv"
	"strings"
)

type SimpleServer struct {
	port int

	hserver http.Server
}

func (s *SimpleServer) startListen() {
	s.hserver = http.Server{
		Addr: ":" + strconv.Itoa(port),
		Handler: http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
			if strings.Index(r.URL.String(), "test") > 0 {
				fmt.Fprintf(w, "这里是")
				return
			}
		}),
	}
	s.hserver.ListenAndServe()
	log.Println("创建 HTTP 服务")
}

func StartServer(port int) {
	s := &SimpleServer{}
	s.port = port
	s.startListen()
}
