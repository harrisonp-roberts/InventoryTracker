function login() {
    const un = document.getElementById("username").value;
    const pw = document.getElementById("password").value;
    let valid = true;

    if(un == null) {
        valid = false;
        alert("Please enter a username");
    }

    if(valid && pw == null) {
        valid = false
        alert("Please enter a password");
    }

    if(valid) {
        const url = "http://localhost:8080/login";
        const data = new UserEntity(un, pw, "");
        let x = postData(url, data);

        console.log(x);
    }
}