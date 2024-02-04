# Zephyr API:

Api for the Arffornia ecosystem.

Request game files, player info and more...

## Usage:

### Request to api:
- `/public/{filename}`: get a file into the public directory.
- `/files/{request name}`: get files manifest of a request alias.
- `/modlist/{request name}`: get modlist manifest of request alias (Curse Forge file).

### Example responses:
#### - `files` request, 200 response
```json
{    
    "tempad-forge-1.20.1-2.3.1.jar": {
      "filename": "tempad-forge-1.20.1-2.3.1.jar",
      "size": 192425,
      "hash": "1df80d8b935bd339c0fe3508e9c03b4a48f4b975",
      "hashAlgo": "SHA-1"
    },  
    "thermal_dynamics-1.20.1-11.0.0.21.jar": {
      "filename": "thermal_dynamics-1.20.1-11.0.0.21.jar",
      "size": 622840,
      "hash": "b3709affeeba600e9968d9caf53a8bf092597059",
      "hashAlgo": "SHA-1"
    }
}
```

#### - `modlist` request, 200 response
```json
{
  "CMF": {
    "fileId":"45234",
    "projectId":"422424"
  },
  "Mekanism": {
    "fileId":"426536",
    "projectId":"24246674"
  }
  
}
```

### Server code response:
- `200`: Success.
- `403`: Forbidden, `isShared` request is set to `false`.
- `404`: File or Request not found.
- `405`: Bad server response.
- `500`: Server internal error.


### Internal server commands:
- `addMod [request alias] [mod name] [file id] [project id]`: add a mod to the mod list.
- `addRequest [files|modList] [request alias] [target folder (if files request)]`: add a new request to database.
- `help`: type help for help.
- `start`: start the server.
- `stop`: stop the server.
- `requestList [files|modList]`: list all the request in the database.
- `reloadDB [files|modList]`: reload the database.
- `saveDB [files|modList]`: save the database.
- `setSharedState [modList|files] [true|false] [request alias]`: set the shared statue of a request.




## Public Directory:

After the first startup, you'll find a `public/` directory at the root.
Please note that all files in this directory must be considered as accessible from external sources!

## Database:

You will find the request database in the cache directory, at:
- `/cache/filesDB.json`
- `/cache/modList.json`


## License:

This project is licensed under the MIT licence. You can consult the complete text of the licence in the file [LICENSE](LICENSE).
