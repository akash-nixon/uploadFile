import logo from './logo.svg';
import './App.css';
import React, {useState} from "react";
import {SyntheticEvent} from "react";

function App() {
  const [selectedFile, setSelectedFile] = useState();
  const [isFilePicked, setIsFilePicked] = useState(false);

  const changeHandler = (event) => {
    setSelectedFile(event.target.files[0]);
    setIsFilePicked(true);
    data = selectedFile;
  };

  // const handleSubmission = () => {
  //   const formData = new FormData();
  //
  //   formData.append('File', selectedFile);
  //
  //   fetch(
  //       'https://freeimage.host/api/1/upload?key=<YOUR_API_KEY>',
  //       {
  //         method: 'POST',
  //         body: formData,
  //       }
  //   )
  //       .then((response) => response.json())
  //       .then((result) => {
  //         console.log('Success:', result);
  //       })
  //       .catch((error) => {
  //         console.error('Error:', error);
  //       });
  // };
  let data = new URLSearchParams();


  const handleSubmission = async (e: SyntheticEvent) => {
    e.preventDefault();
    console.log("inside submit");
    const response = await fetch("http://localhost:9191/image/filesys", {
      method: "POST",
      body: data,
    });

    // if (response.ok) {
    //   router.push("/dashboard");
    // }
  };

  return (
      <div>
        <form action="..." method="post" encType="multipart/form-data">
        <input type="file" name="file" onChange={changeHandler} />
        </form>
        {isFilePicked ? (
            <div>
              <p>Filename: {selectedFile.name}</p>
              <p>Filetype: {selectedFile.type}</p>
              <p>Size in bytes: {selectedFile.size}</p>
              <p>
                lastModifiedDate:{' '}
                {selectedFile.lastModifiedDate.toLocaleDateString()}
              </p>
            </div>
        ) : (
            <p>Select a file to show details</p>
        )}
        <div>
          <button onClick={handleSubmission}>Submit</button>

        </div>
        <form action="..." method="post" encType="multipart/form-data">
          <input type="file" name="file"/>
        </form>
      </div>
  );
}

export default App;
