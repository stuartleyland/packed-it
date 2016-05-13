import React     from 'react';
import { Route } from 'react-router';


import App from './components/App.js';
import ListDetail from './components/ListDetail.js';
import ListEdit from './components/ListEdit.js';

export default (
  [
    <Route path="/" component={App}/>,
    <Route path="/list-detail/:listId" component={ListDetail} />,
    <Route path="/list-edit" component={ListEdit} />
  ]
);
