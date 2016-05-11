import React from 'react';
import ReactDOM from 'react-dom';
import moment from 'moment';

var Lists = React.createClass({
  getInitialState: function() {
    return {lists : []};
  },
  componentDidMount: function() {
    $.ajax({
      url: "/api/v1/lists",
      dataType: 'json',
      cache: false,
      success: function(data) {
        this.setState({lists: data});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error("/api/v1/lists", status, err.toString());
      }.bind(this)
    });
  },
  render: function() {
    var listNodes = this.state.lists.map(function(list) {
      return (<List list={list} key={list.id}/>);
    });
    return (
        <div id="lists">{listNodes}</div>
      )
  }
});

var List = React.createClass({
  render: function() {
    var percentage = Math.round((this.props.list.itemsPacked / this.props.list.totalItems) * 100);
    
    var startDateMoment = moment(this.props.list.startDate);
    var startDateFormatted = startDateMoment.format('ddd DD MMM YYYY');
    var endDateMoment = moment(this.props.list.endDate);
    var endDateFormatted = endDateMoment.format('ddd DD MMM YYYY');
    var numberOfNights = endDateMoment.diff(startDateMoment, "days")
    
    return (
        <div id="list">
          <div className="well well-sm">
            <div className="description"><h2>{this.props.list.description}</h2></div>
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
});

ReactDOM.render(<Lists />, document.getElementById("root"));