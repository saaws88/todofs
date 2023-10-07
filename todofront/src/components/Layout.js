export default function Layout(props) {
  return (
    <div className="layout">
      <h1 className="title">To Do</h1>
      {props.children}
    </div>
  )
}