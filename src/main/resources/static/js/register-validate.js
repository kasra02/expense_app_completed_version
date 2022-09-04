const nameError = document.getElementById("nameError")
const email_error = document.getElementById("email_error")
const password_error = document.querySelector("#password_error")
const confirmPassword_error = document.querySelector("#confirmPassword_error")

const formInput = {
    name:"",
    email:"",
    password:"",
    confirmPassword:"",
}


function validateName() {
    const name = document.querySelector("#form_name").value

    console.log(name)
    console.log(nameError)

    // if (name.length === 0){
    //     name_error.innerHTML = "name is Required"
    //     return false
    // }
    // if (!name.match("/^[a-zA-z]*\s{1}[a-zA-z]*$/")){
    //     name_error.innerHTML = "write full name"
    //     return false
    // }

    // nameError.innerHTML = "valid";
    return true
}