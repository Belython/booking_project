package by.kanarski.booking.commands.factory;

public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTER, GOTOREGISTRATION, GOTOMAIN, GOTOSELECTHOTEL, GOTOSELECTROOMS, MAKEBILL, GOTOACCOUNT, PAYBILL,
    SETLOCALE, CANCELACTION, REFUSEBILL, GOTOREMINDPASSWORD, REMINDPASSWORD, SETCURRENCY,

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

            case GOTOSELECTHOTEL:
                return new GoToSelectHotelCommand();

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
            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

}
