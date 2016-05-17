import React from 'react';
import ListsOverview from './ListsOverview.js';
import {AUTH0_CLIENT_ID, AUTH0_DOMAIN} from '../../auth0-variables.js';

export default class App extends React.Component {

  constructor() {
    super();
    this.lock = new Auth0Lock(AUTH0_CLIENT_ID, AUTH0_DOMAIN);
    this.showLock = this.showLock.bind(this);
  }

  showLock() {
    this.lock.show();
  }

  render() {
    return (
      <div>
        <div className="login-box">
        <a onClick={this.showLock}>Sign In</a>
        </div>
      	<ListsOverview />
      </div>
    );
  }
}
