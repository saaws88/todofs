import Layout from './components/Layout';
import './App.css';
import ToDoList from './components/ToDoList';

function App() {
  return (
    <div>
      <Layout>
        <ToDoList />
      </Layout>
    </div>
  );
}

export default App;
