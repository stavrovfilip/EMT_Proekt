import './App.css';
import React, {Component} from "react";
import Articles from "../Articles/ArticleList/articles"
import ArticleService from "../../repository/articleRepository";
import UserService from "../../repository/userRepository"
import Header from '../ButtonsManipulation/Header/header';
import Orders from "../Orders/orders";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import ArticleAdd from "../Articles/ArticleAdd/articleAdd";
import ArticleEdit from "../Articles/ArticleEdit/articleEdit";
import Login from "../Authentication/Login";
import Register from "../Authentication/Register"

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
        articles: [],
        users:[],
        selectedArticle: {}
    }
  }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
                <Route path={"/register"} exact render={() => <Register/>}/>
                <Route path={"/login"} exact render={() => <Login/>}/>
                <Route path={"/articles/add"} exact render={() =>
                    <ArticleAdd
                                onAddArticle={this.addArticle}/>}/>
                <Route path={"/articles/edit/:id"} exact render={() =>
                    <ArticleEdit
                                 onEditArticle={this.editArticle}
                                 article={this.state.selectedArticle}/>}/>

                <Route path={"/articles"} exact render={() =>
                  <Articles articles={this.state.articles}
                            getUser={this.getUserById}
                            onDelete={this.deleteArticle}
                            onEdit={this.getArticle}/>
                  }/>
              <Route path={"/orders"} exact render={() =>
                  <Orders orders={this.state.orders}/>}/>
              <Redirect to={"/articles"}/>
            </div>
          </main>
        </Router>
    );
  }

    checkIfUserIsValid = async () => {

        let token = localStorage.getItem("token");
        //if the token exist check the validity of the token
        if(token){
            let validity = await UserService.checkUserValidity(token);
            //if the validity is ok fetch the logged user into app
            if(validity){
                UserService.getUserByToken()
                    .then( data => {
                        let userStringify = JSON.stringify(data.data);
                        localStorage.setItem("user",userStringify);
                    })
            }
            else {
                localStorage.removeItem("token");
                this.setState({
                    user:{}
                })
            }
        }
    }

  componentDidMount() {
    this.loadArticles();
    setInterval(this.checkIfUserIsValid,1000 * 60 * 60 * 10);
  }

  loadArticles = () => {
    ArticleService.fetchArticles()
        .then((data) => {
          this.setState({
            articles: data.data
          })
        });
  }

    deleteArticle = (id) => {
        ArticleService.deleteArticle(id)
            .then(() => {
                this.loadArticles();
            });
    }

    addArticle = (title, description, amount, currency, sales) => {
        ArticleService.addArticle(title, description, amount, currency, sales)
            .then(() => {
                this.loadArticles();
            });
    }

    getArticle = (id) => {
        ArticleService.getArticle(id)
            .then((data) => {
                this.setState({
                    selectedArticle: data.data
                })
            })
    }

    editArticle = (id, title, description, amount, currency, sales) => {
        ArticleService.editArticle(id, title, description, amount, currency, sales)
            .then(() => {
                this.loadArticles();
            });
    }

    getUserById = async (userId) => {
        console.log("getUserId App");
        return (await UserService.getUserById(userId)).data;
    }

}

export default App;
