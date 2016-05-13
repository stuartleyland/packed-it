import React from 'react';

export default class ListEdit extends React.Component {
	
	constructor() {
		super();
	}
	
	render() {
		return (
			<form>
				<div className="form-group">
					<label for="description">Description</label>
					<input type="text" className="form-control" id="description" placeholder="Description" />
				</div>
				<div className="form-group">
					<label for="startDate">Start Date</label>
					<input type="date" className="form-control" id="startDate" />
				</div>
			</form>
		);
	}
}