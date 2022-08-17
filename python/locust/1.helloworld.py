from locust import HttpUser, task

class HelloWorldUser(HttpUser):
    @task
    def hello_world(self):
        url = 'baidu.com'
        self.client.get(url + "/hello")
        self.client.get(url + "/world")