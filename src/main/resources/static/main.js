import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, hashHistory } from 'react-router'
import App from './components/App.js';
import ListDetail from './components/ListDetail.js';

ReactDOM.render(
	<Router history={hashHistory}>
    	<Route path="/" component={App}/>
    	<Route path="/list-detail" component={ListDetail} />
  	</Router>, 
	document.getElementById("app"));