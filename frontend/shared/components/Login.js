import React from 'react';

export default class Login extends React.Component {

  constructor() {
    super();
    this.state = { username : "", password : "" };

    this.handleLogin = this.handleLogin.bind(this);
    this.handleUsernameChange = this.handleUsernameChange.bind(this);
    this.handlePasswordChange = this.handlePasswordChange.bind(this);
  }

  handleUsernameChange(event) {
    this.setState({username : event.target.value});
  }

  handlePasswordChange(event) {
    this.setState({password : event.target.value});
  }

  handleLogin(event) {
    event.preventDefault();
    console.log("Username = " + this.state.username);
    console.log("Password = " + this.state.password);

    $.ajax({
      url: "http://localhost:8080/auth",
      contentType: 'application/json',
      dataType: 'json',
      type: 'POST',
      data: JSON.stringify(this.state),
      cache: false,
      success: (data) => {
        console.log("Success! Response = " + data.token);
        this.props.onLoginSuccess(data.token);
      },
      error: (xhr, status, err) => {
        console.error("Error", status, err.toString());
      }
    });
  }

  render() {
    return (
      <form onSubmit={this.handleLogin}>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input type="text" className="form-control" id="username" placeholder="Username" onChange={this.handleUsernameChange}></input>
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input type="password" className="form-control" id="password" placeholder="Password" onChange={this.handlePasswordChange}></input>
        </div>
        <button type="submit" className="btn btn-default">Submit</button>
      </form>
    );
  }
}
