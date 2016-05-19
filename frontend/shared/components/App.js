import React from 'react';
import ListsOverview from './ListsOverview.js';
import SecuredPing from './SecuredPing.js';

export default class App extends React.Component {

  render() {
    return (
      <div>
        <SecuredPing />
        <ListsOverview />
      </div>
    );
  }
}
