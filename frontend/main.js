import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, browserHistory } from 'react-router'
import App from './components/App.js';
import ListDetail from './components/ListDetail.js';
import ListEdit from './components/ListEdit.js';

ReactDOM.render(
	<Router history={browserHistory}>
    	<Route path="/" component={App}/>
    	<Route path="/list-detail/:listId" component={ListDetail} />
    	<Route path="/list-edit" component={ListEdit} />
  	</Router>, 
	document.getElementById("app"));