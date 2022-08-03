

import './App.css';
import { BrowserRouter as Router, Switch, Route, Routes } from 'react-router-dom';

import VocabularyFeature from './features/Vocabulary';

function App() {

  return (
    <div className="App">
      <Router>
        {/* <Sidebar /> */}
        <VocabularyFeature />

      </Router>

    </div>
  );
}

export default App;
