import React, {Component} from 'react';
import './Chat.css';

class Chat extends Component {

    constructor() {
        super();

        this.state = {
            hidden: true
        }
        this.showChat = this.showChat.bind(this);
    }

    showChat(e) {
        let newState = this.state.hidden ? false : true;
        if (newState) {
            document.body.style.overflow = "auto";
        }
        else {
            document.body.style.overflow = "hidden";
        }
        this.setState({hidden: newState});
    }

    render() {
        if (!this.state.hidden) {
            return (
                <div>
                    <div className="chatBackground"></div>
                    <div className="chatContainer">
                        <button type="button"
                            style={{transform: "translateX(-250px)"}}
                            className="btnChat glyphicon glyphicon-chevron-right"
                            onClick={this.showChat}
                        ></button>
                        <div className="chatMessages">
                            <div className="chatMessageOther">
                                <div className="chatMessageUser">User1234</div>
                                Hey waddup pimps
                            </div>
                            <div className="chatMessageOther">
                                <div className="chatMessageUser">User1234</div>
                                Hey waddup pimps
                            </div>
                            <div className="chatMessageOwn">
                                <div className="chatMessageUser">User1234</div>
                                Heyoooooo wassupp
                            </div>
                            <div className="chatMessageOther">
                                <div className="chatMessageUser">User1234</div>
                                Hey waddup pimps
                            </div>
                            <div className="chatMessageOwn">
                                <div className="chatMessageUser">User1234</div>
                                Heyoooooo wassupp
                            </div>
                            <div className="chatMessageOther">
                                <div className="chatMessageUser">User1234</div>
                                Hey waddup pimps
                            </div>
                            <div className="chatMessageOwn">
                                <div className="chatMessageUser">User1234</div>
                                Heyoooooo wassupp
                            </div>
                            <div className="chatMessageOther">
                                <div className="chatMessageUser">User1234</div>
                                Hey waddup pimps
                            </div>
                            <div className="chatMessageOwn">
                                <div className="chatMessageUser">User1234</div>
                                Heyoooooo wassupp
                            </div>
                            <div className="chatMessageOwn">
                                <div className="chatMessageUser">User1234</div>
                                Heyoooooo wassupp
                            </div>
                        </div>
                        <div className="chatInputs">
                            <input type="text" maxLength="240" className="chatTextInput" />
                            <button className="chatButtonInput glyphicon glyphicon-send"></button>
                        </div>
                    </div>
                </div>
            );
        }

        return (
            <button type="button"
                className="btnChat glyphicon glyphicon-chevron-left"
                onClick={this.showChat}
            ></button>
        );
    }
}

export default Chat;
