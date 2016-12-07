package by.kanarski.booking.commands.factory;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.impl.admin.GoToAdminPageCommand;
import by.kanarski.booking.commands.impl.admin.database.ConstrainRowCommand;
import by.kanarski.booking.commands.impl.admin.database.hotel.AlterHotelsCommand;
import by.kanarski.booking.commands.impl.admin.database.hotel.GoToHotelsRedactorCommand;
import by.kanarski.booking.commands.impl.admin.database.location.AlterLocationsCommand;
import by.kanarski.booking.commands.impl.admin.database.location.GoToLocationsRedactorCommand;
import by.kanarski.booking.commands.impl.admin.database.room.AlterRoomsCommand;
import by.kanarski.booking.commands.impl.admin.database.room.GoToRoomsRedactorCommand;
import by.kanarski.booking.commands.impl.admin.database.roomType.AlterRoomTypesCommand;
import by.kanarski.booking.commands.impl.admin.database.roomType.GoToRoomTypesRedactorCommand;
import by.kanarski.booking.commands.impl.admin.database.user.AlterUsersCommand;
import by.kanarski.booking.commands.impl.admin.database.user.GoToUsersRedactorCommand;
import by.kanarski.booking.commands.impl.client.*;
import by.kanarski.booking.commands.impl.user.*;

public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTER, GOTOREGISTRATION, GOTOMAIN, GOTOSEARCHRESULTS, GOTOSELECTROOMS, MAKEBILL, GOTOACCOUNT, PAYBILL,
    SETLOCALE, CANCELACTION, REFUSEBILL, GOTOREMINDPASSWORD, REMINDPASSWORD, SETCURRENCY,
    GETDESTINATIONS,

    //admin commands
    GOTOADMINPAGE,
    GOTOROOMSREDACTOR, ALTERROOMS,
    GOTOROOMTYPEREDACTOR, ALTERROOMTYPE,
    GOTOLOCATIONSREDACTOR, ALTERLOCATIONS,
    GOTOUSERSREDACTOR, ALTERUSERS,
    GOTOHOTELSREDACTOR, ALTERHOTELS,
    CONSTRAINROW;

    public ICommand getCurrentCommand() throws EnumConstantNotPresentException {
        switch (this) {
            case LOGIN:
                return new LoginUserCommand();

            case LOGOUT:
                return new LogoutUserCommand();

            case REGISTER:
                return new RegisterCommand();

            case GOTOREGISTRATION:
                return new GoToRegistrationCommand();

            case GOTOMAIN:
                return new GoToMainPageCommand();

            case SETLOCALE:
                return new SetLocaleCommand();

            case GOTOSEARCHRESULTS:
                return new GoToSearchResultsCommand();

            case GOTOSELECTROOMS:
                return new GoToSelectRoomsCommand();

            case MAKEBILL:
                return new MakeBillCommand();

            case GOTOACCOUNT:
                return new GoToAccountCommand();

            case PAYBILL:
                return new PayBillCommand();

            case CANCELACTION:
                return new CancelActionCommand();

            case GOTOADMINPAGE:
                return new GoToAdminPageCommand();

            case GOTOROOMSREDACTOR:
                return new GoToRoomsRedactorCommand();

            case ALTERROOMS:
                return new AlterRoomsCommand();

            case REFUSEBILL:
                return new RefuseBillCommand();

            case GOTOREMINDPASSWORD:
                return new GoToRemindPasswordCommand();

            case REMINDPASSWORD:
                return new RemindPasswordCommand();

            case SETCURRENCY:
                return new SetCurrencyCommand();

            case CONSTRAINROW:
                return new ConstrainRowCommand();

            case GOTOROOMTYPEREDACTOR:
                return new GoToRoomTypesRedactorCommand();

            case ALTERROOMTYPE:
                return new AlterRoomTypesCommand();

            case GOTOLOCATIONSREDACTOR:
                return new GoToLocationsRedactorCommand();

            case ALTERLOCATIONS:
                return new AlterLocationsCommand();

            case GOTOUSERSREDACTOR:
                return new GoToUsersRedactorCommand();

            case ALTERUSERS:
                return new AlterUsersCommand();

            case GOTOHOTELSREDACTOR:
                return new GoToHotelsRedactorCommand();

            case ALTERHOTELS:
                return new AlterHotelsCommand();

            case GETDESTINATIONS:
                return new GetDestinationsCommand();
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

}
