package admin

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"time"
)

type User struct {
	FirstName string    `json:"first_name"`
	LastName  string    `json:"last_name"`
	Email     string    `json:"email"`
	CreatedAt time.Time `json:"created_at"`
}

type fooHandler struct{}

func (f *fooHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	user := new(User)
	err := json.NewDecoder(r.Body).Decode(user)
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
		fmt.Fprint(w, err)
		return
	}
	user.CreatedAt = time.Now()
	data, _ := json.Marshal(user)
	w.Header().Add("content-type", "application/json")
	w.WriteHeader(http.StatusCreated)

	fmt.Fprint(w, string(data))
}

func indexHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Println(r.URL)
	fmt.Println("indexHandler 실행")
	fmt.Fprint(w, "Hello World")
}

func barHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Println("barHandler 실행")
	name := r.URL.Query().Get("name")
	if name == "" {
		name = "yubin"
	}

	fmt.Fprintf(w, "Hello %s!", name)
}

func nodeHandler(w http.ResponseWriter, r *http.Request) {
	url := "http://localhost:3001/api" // Node.js 마이크로서비스의 API 경로
	req, err := http.NewRequest("GET", url, nil)

	if err != nil {
		fmt.Println(err)
		return
	}

	client := &http.Client{}
	resp, err := client.Do(req)

	if err != nil {
		fmt.Println(err)
		return
	}

	defer resp.Body.Close()

	body, err := ioutil.ReadAll(resp.Body)

	if err != nil {
		fmt.Println(err)
		return
	}

	fmt.Fprint(w, string(body))
}

func NewHttpHandler() http.Handler {
	mux := http.NewServeMux()

	mux.HandleFunc("/go/bar", barHandler)
	mux.HandleFunc("/", indexHandler)
	mux.Handle("/go/foo", &fooHandler{})

	mux.HandleFunc("/go/to-node-example", nodeHandler)

	return mux
}
