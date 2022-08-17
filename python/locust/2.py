import time
from locust import HttpUser, task, between

# 模拟用户操作的类，继承自 HttpUser
class QuickstartUser(HttpUser):
    # 等待时间，模拟一个用户操作一个接口之后等待的时间
    wait_time = between(1, 5)

    @task # 装饰方法，表示该方法是用户的操作
    def hello_world(self):
        self.client.get("/hello")
        self.client.get("/world")
    
    @task(3)
    def view_items(self):
        for item_id in range(10):
            self.client.get(f"/item?id={item_id}", name = "/item")
            time.sleep(1)
    
    def on_start(self):
        self.client.post("/user", json = {"username": "foo", "password": "bar"})