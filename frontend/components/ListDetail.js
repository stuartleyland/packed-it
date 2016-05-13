import React from 'react';

export default class ListDetail extends React.Component {
	
	constructor() {
		super();
	}
	
	render() {
		return <div>Detailed list view for list with ID {this.props.params.listId}</div>
	}
}