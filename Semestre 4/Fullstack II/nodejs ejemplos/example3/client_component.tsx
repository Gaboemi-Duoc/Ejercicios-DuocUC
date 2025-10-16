"use client";

import { useState } from "react";
import Button from "react-bootstrap/Button";

export function Contador() {
  const [count, setCount] = useState(10);
  function addCount() {
    setCount(count + 1);
  }
  function minusCount() {
    setCount(count - 1);
  }

  return (
    <>
      <Button variant="outline-success" onClick={addCount}>Suma</Button>
      <h1>El Contador es {count}</h1>
      <Button variant="outline-danger" onClick={minusCount}>Resta</Button>
    </>
  );
}
