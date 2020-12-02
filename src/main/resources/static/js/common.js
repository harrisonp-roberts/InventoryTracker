function postData(url, data) {
    console.log("URL: " + url);

    return fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });
}