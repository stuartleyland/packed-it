import React from 'react';
import ListSummary from './ListSummary.js';

export default class ListsOverview extends React.Component {
  constructor() {
    super();
    this.state = {lists : []};
  }

  componentDidMount() {
    $.ajax({
      url: "/api/v1/lists",
      dataType: 'json',
      cache: false,
      success: (data) => {
        this.setState({lists: data});
      },
      error: (xhr, status, err) => {
        console.error("/api/v1/lists", status, err.toString());
      }
    });
  }

  render() {
    var listNodes = this.state.lists.map(list => <ListSummary list={list} key={list.id}/>);
    return (
        <div id="lists">{listNodes}</div>
      )
  }
}