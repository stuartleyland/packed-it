import React from 'react';

export default class SecuredPing extends React.Component {

  constructor() {
    super();

    this.state = { response : "" };
  }

  componentDidMount() {
    $.ajax({
      url: "http://localhost:8080/secured/ping",
      dataType: 'text',
      cache: false,
      success: (data) => {
        this.setState({response: data});
      },
      error: (xhr, status, err) => {
        console.error("/secured/ping", status, err.toString());
      }
    });
  }


  render() {
    return (
      <div>
        {this.state.response}
      </div>
    );
  }
}
