
interface Person{
    firstName: String;
    lastName: String;
}

let user3 = {
    firstName : "Jane",
    lastName: "User"
}

document.body.innerHTML = greeter(user3);