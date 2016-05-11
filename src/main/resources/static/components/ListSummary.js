import React from 'react';
import moment from 'moment';
import {Link} from 'react-router';

export default class ListSummary extends React.Component {

  constructor() {
    super();
  }

  render() {
    var percentage = Math.round((this.props.list.itemsPacked / this.props.list.totalItems) * 100);
    
    var startDateMoment = moment(this.props.list.startDate);
    var startDateFormatted = startDateMoment.format('ddd DD MMM YYYY');
    var endDateMoment = moment(this.props.list.endDate);
    var endDateFormatted = endDateMoment.format('ddd DD MMM YYYY');
    var numberOfNights = endDateMoment.diff(startDateMoment, "days")
    
    return (
        <div id="list">
          <div className="well well-sm">
            <div className="description"><h2><Link to="/list-detail">{this.props.list.description}</Link></h2></div>
            <div className="dates">
              <h5>{startDateFormatted} - {endDateFormatted} ({numberOfNights} nights)</h5>
            </div>
            <div className="packing-summary">{this.props.list.totalItems} items / {this.props.list.itemsPacked} packed</div>
            <div className="progress">
              <div className="progress-bar" 
                role="progressbar" 
                aria-valuenow={this.props.list.itemsPacked}
                aria-valuemin="0" 
                aria-valuemax={this.props.list.totalItems}
                style={{width: percentage + "%"}}>
                <span class="sr-only">{percentage}%</span>
              </div>
            </div>
          </div>
        </div>
      );
  }
}