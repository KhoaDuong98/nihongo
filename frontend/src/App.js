

import './App.css';
import { BrowserRouter as Router, Switch, Route, Routes } from 'react-router-dom';

import VocabularyFeature from './features/Vocabulary';
import { useEffect } from 'react';
import NotFound from './features/Vocabulary/page/NotFound';

function App() {

  useEffect(() => {
    document.title = 'Nihongoooo'
  }, [])

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
