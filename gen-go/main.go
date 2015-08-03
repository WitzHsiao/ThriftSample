package main

import "./echo"

func main() {
	host := "localhost:9090"
	server := echo.NewEchoServer(host)
	server.Run()
}
