package main

import (
	"admin-server/admin"
	"log"
	"net/http"

	"github.com/hashicorp/consul/api"
)

func main() {
	config := api.DefaultConfig()
	client, err := api.NewClient(config)

	serviceID := "admin-server"
	serviceName := "callisto-admin-server"
	serviceAddress := "127.0.0.1"
	servicePort := 3000

	registration := new(api.AgentServiceRegistration)
	registration.ID = serviceID
	registration.Name = serviceName
	registration.Address = serviceAddress
	registration.Port = servicePort

	agent := client.Agent()
	err = agent.ServiceRegister(registration)
	if err != nil {
		log.Fatal("Falied to register service to Consul")
	}

	http.ListenAndServe(":3000", admin.NewHttpHandler())
}
