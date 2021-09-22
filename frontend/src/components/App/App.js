import './App.css';
import React, {Component} from "react";
import Articles from "../Articles/articles"
import ArticleService from "../../repository/articleRepository";
import Header from '../Header/header';
import Orders from "../Orders/orders"
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      articles: [],
      orders: []
    }
  }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
              <Route path={"/articles"} exact render={() =>
                  <Articles articles={this.state.articles}/>}/>
              <Route path={"/orders"} exact render={() =>
                  <Orders orders={this.state.orders}/>}/>
              <Redirect to={"/articles"}/>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    this.loadArticles();
    this.loadOrders();
  }

  loadArticles = () => {
    ArticleService.fetchArticles()
        .then((data) => {
          this.setState({
            articles: data.data
          })
        });
  }

  loadOrders = () => {
    ArticleService.fetchOrders()
        .then((data) => {
          this.setState({
            orders: data.data
          })
        });
  }

}

export default App;
