# astah-miro Plugin
## About

The plugin that enables import/export of diagrams between astah* and miro.

Following items can be import/export.
- Importing from miro
    - class diagram
    - mindmap
- Exporting to miro
    - class diagram

*Note:* This plugin is an experimental version and is not provided product support. 

## How to install

1. Download the .jar file from [releases](https://github.com/ChangeVision/astah-miro-plugin/releases).
1. Start astah.
1. Install the plugin at the Plugin List Dialog ([Plugin] -> [Installed Plugins] -> [Install])
1. Restart astah.

## Setup

Create a configuration file `.astah-miro.json` under the home directory(`$HOME`) and edit the below contents.

```json
{
  "token": "Access Token",
  "boardId": "Board Id"
}
```

### How to get an Access Token
We assume that you have already created a miro account.

#### Step 1 - Get Developer Team

Open this link to create a development team.

https://miro.com/app/dashboard/?createDevTeam=1

#### Step 2 - Create an App

1. Open [this link](https://miro.com/app/settings/user-profile/apps)
1. Click `[Create new app]` on the page that opens.
1. Enter `App name` and `Description` in the dialog and click `[Create App]`.


#### Step 3 - Set the app permissions and get the token

1. Check the required permissions (`boards:read` and `boards:write`) from the middle of the page after Step2 in `OAuth scopes`.
1. Click `Install app to get OAuth token`.
1. Click `[Install]` of the Team in the dialog.
    - The team you select here will not be the `Dev team`, but the team whose board you actually want to work with astah*
1. Add the displayed `Access Token`.
    - Please don't expose the token displayed here.
1. Edit the Access Token in `"token"` of `.astah-miro.json`.

See also: https://developers.miro.com/docs/getting-started#section-step-4-install-app-to-test-rest-api-or-web-plugin


## Usage

### Import

1. Open an empty diagram from miro's board with astah* (`[Diagram] -> [Class Diagram] or [Mind Map]`)
1. `[Tools] -> [miro] -> [Import diagrams from miro]`

You must have the diagram open before you can import it.

SysML is only support for mindmap.

### Export

1. open the diagram you want to export to miro with astah*.
1. `[Tools] -> [miro] -> [Export diagram to miro]`


## License

Copyright 2020 Change Vision, Inc.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with the License. You may obtain a copy of the License in the LICENSE file, or at:

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.