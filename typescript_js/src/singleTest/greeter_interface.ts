
interface Person{
    firstName: String;
    lastName: String;
}

function greeter(person: Person){
    return "Hello, " + person.firstName + " " + person.lastName;
}

let user3 = {
    firstName : "Jane",
    lastName: "User"
}

document.body.innerHTML = greeter(user3);