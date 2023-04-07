package main

import (
	"admin-server/admin"
	"context"
	"fmt"
	"log"
	"net/http"
	"sync"

	"github.com/hashicorp/consul/api"
	"github.com/segmentio/kafka-go"
)

func main() {
	// consul 설정 및 등록
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

	// http 서버 실행
	go func() {
		log.Fatal(http.ListenAndServe(":3000", admin.NewHttpHandler()))
	}()

	// kafka 구독
	fmt.Println("kafka on")
	topic := "example-topic"
	partition := 0

	conn, err := kafka.DialLeader(context.Background(), "tcp", "localhost:9092", topic, partition)
	if err != nil {
		log.Fatal("failed to dial leader", err)
	}

	defer conn.Close()

	reader := kafka.NewReader(kafka.ReaderConfig{
		Brokers: []string{"localhost:9092"},
		Topic:   topic,
	})

	var wg sync.WaitGroup
	wg.Add(1)

	// 별도 고루틴에서 Kafka Consumer 실행
	go func() {
		defer wg.Done()

		for {
			m, err := reader.ReadMessage(context.Background())
			if err != nil {
				log.Fatal("failed to read message: ", err)
			}

			fmt.Printf("received message: %s\n", string(m.Value))
		}
	}()

	// 프로그램 종료를 기다림
	wg.Wait()
}
