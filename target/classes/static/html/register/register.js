window.onload = () => {
}

function register() {
    const un = document.getElementById("username").value;
    const p1 = document.getElementById("password").value;
    const p2 = document.getElementById("confirm").value;
    const type = document.getElementsByName("type");
    let selectedType;
    let valid = true;

    for(let i = 0; i < type.length; i++) {
        if(type[i].checked) {
            selectedType = type[i].value;
        }
    }

    if(p1 == null) {
        alert("Please enter a password");
        valid = false;
    }

    if(valid && un == null) {
        alert("Please enter a valid username");
        valid = false;
    }

    if(valid && selectedType !== "basic" && selectedType !== "admin") {
        alert("Please select a user type");
        valid = false;
    }

    if(valid) {
        const entity = new UserEntity(un, p1, selectedType);
        const url = "http://localhost:8080/register";

        let x = postData(url, entity);

        console.log(x);
    }
}