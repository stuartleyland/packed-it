import React from 'react';
import ListsOverview from './ListsOverview.js';
import SecuredPing from './SecuredPing.js';
import Login from './Login.js';

export default class App extends React.Component {

  constructor() {
    super();
    this.state = { apiToken : "" };
    this.setApiToken = this.setApiToken.bind(this);
  }

  setApiToken(token) {
    console.log("Setting token to: " + token);
    this.setState({apiToken : token});
  }

  render() {
    var componentToShow;
    if (!this.state.apiToken) {
      componentToShow = <Login onLoginSuccess={this.setApiToken}/>;
    }
    else {
      componentToShow = <ListsOverview apiToken={this.state.apiToken}/>;
    }

    return (
      <div>
        {componentToShow}
      </div>
    );
  }
}
