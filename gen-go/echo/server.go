package echo

import (
	"fmt"

	"git-wip-us.apache.org/repos/asf/thrift.git/lib/go/thrift"
)

type EchoServer struct {
	host             string
	handler          *EchoHandler
	processor        *EchoSrvProcessor
	transport        *thrift.TServerSocket
	transportFactory thrift.TTransportFactory
	protocolFactory  *thrift.TBinaryProtocolFactory
	server           *thrift.TSimpleServer
}

func NewEchoServer(host string) *EchoServer {
	handler := NewEchoHandler()
	processor := NewEchoSrvProcessor(handler)
	transport, err := thrift.NewTServerSocket(host)
	if err != nil {
		panic(err)
	}

	transportFactory := thrift.NewTTransportFactory()
	protocolFactory := thrift.NewTBinaryProtocolFactoryDefault()
	server := thrift.NewTSimpleServer4(processor, transport, transportFactory, protocolFactory)
	return &EchoServer{
		host:             host,
		handler:          handler,
		processor:        processor,
		transport:        transport,
		transportFactory: transportFactory,
		protocolFactory:  protocolFactory,
		server:           server,
	}
}

func (es *EchoServer) Run() {
	fmt.Printf("server listening on %s\n", es.host)
	es.server.Serve()
}

func (es *EchoServer) Stop() {
	fmt.Println("stopping server...")
	es.server.Stop()
}
